package uz.javokhirdev.randoms.data.base

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import uz.javokhirdev.randoms.core.helpers.DispatcherProvider
import uz.javokhirdev.randoms.data.UIState
import uz.javokhirdev.randoms.data.model.RandomModel
import uz.javokhirdev.randoms.data.network.RandomListResponse

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow<UIState<T>> {
        emit(UIState.success(request()))
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.failure(exception.toMessage()))
    }

    protected fun <T : DataMapper<T, S>, S> doNetworkRequest(
        request: suspend () -> Response<T>
    ) = flow {
        request().let {
            if (it.isSuccessful && it.body() != null) {
                emit(UIState.success(it.body()?.mapToDomain()))
            } else {
                emit(UIState.failure(it.errorBody().toMessage()))
            }
        }
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.failure(exception.toMessage()))
    }

    protected fun doNetworkRequestList(
        request: suspend () -> Response<List<RandomListResponse>>
    ) = flow {
        request().let {
            if (it.isSuccessful && it.body() != null) {
                emit(UIState.success(it.body()?.firstOrNull()?.mapToDomain()))
            } else {
                emit(UIState.failure(it.errorBody().toMessage()))
            }
        }
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.failure(exception.toMessage()))
    }

    protected fun doNetworkRequestStringList(
        request: suspend () -> Response<List<String>>
    ) = flow {
        request().let {
            if (it.isSuccessful && it.body() != null) {
                emit(UIState.success(RandomModel(title = it.body()?.firstOrNull())))
            } else {
                emit(UIState.failure(it.errorBody().toMessage()))
            }
        }
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.failure(exception.toMessage()))
    }

    private fun Throwable?.toMessage(): String {
        return this?.localizedMessage.orEmpty().ifEmpty { "Error Occurred!" }
    }

    private fun ResponseBody?.toMessage(): String {
        return this?.string().orEmpty().ifEmpty { "Error Occurred!" }
    }
}
