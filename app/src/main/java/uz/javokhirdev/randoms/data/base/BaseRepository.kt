package uz.javokhirdev.randoms.data.base

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import uz.javokhirdev.randoms.core.helpers.DispatcherProvider
import uz.javokhirdev.randoms.data.UIState

abstract class BaseRepository {

    /**
     * Do request
     *
     * @return result in [flow] with [UIState]
     */
    protected fun <T> doRequest(request: suspend () -> T) = flow<UIState<T>> {
        emit(UIState.success(request()))
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.Failure(exception.toMessage()))
    }

    /**
     * Do network request
     *
     * @return result in [flow] with [UIState]
     */
    protected fun <T : DataMapper<T, S>, S> doNetworkRequest(
        request: suspend () -> Response<T>
    ) = flow {
        request().let {
            if (it.isSuccessful && it.body() != null) {
                emit(UIState.Success(it.body()?.mapToDomain()))
            } else {
                emit(UIState.Failure(it.errorBody().toMessage()))
            }
        }
    }.flowOn(DispatcherProvider.IO).catch { exception ->
        emit(UIState.Failure(exception.toMessage()))
    }

    private fun Throwable?.toMessage(): String {
        return this?.localizedMessage.orEmpty().ifEmpty { "Error Occurred!" }
    }

    private fun ResponseBody?.toMessage(): String {
        return this?.string().orEmpty().ifEmpty { "Error Occurred!" }
    }
}
