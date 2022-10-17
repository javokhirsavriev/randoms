package uz.javokhirdev.randoms.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.javokhirdev.randoms.data.model.ListModel
import uz.javokhirdev.randoms.data.model.RandomType

class ListViewModel : ViewModel() {

    private val listData = MutableStateFlow(emptyList<ListModel>())
    val list = listData.asStateFlow()

    fun getPictures() {
        viewModelScope.launch { listData.value = picturesList }
    }

    fun getFacts() {
        viewModelScope.launch { listData.value = factsList }
    }

    fun getJokes() {
        viewModelScope.launch { listData.value = jokesList }
    }

    fun getQuotes() {
        viewModelScope.launch { listData.value = quotesList }
    }

    fun getMoreData() {
        viewModelScope.launch { listData.value = moreList }
    }

    private val picturesList
        get() = listOf(
            ListModel(
                id = 1,
                title = "Cats pictures and gifs",
                cover = "file:///android_asset/pictures/image_01.webp",
                baseUrl = "https://cataas.com/cat",
                isImageUrl = true,
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 3,
                title = "Pictures of dogs",
                cover = "file:///android_asset/pictures/image_03.webp",
                baseUrl = "https://random.dog/woof.json",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 4,
                title = "Pictures of ducks",
                cover = "file:///android_asset/pictures/image_04.webp",
                baseUrl = "https://random-d.uk/api/v2/random",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 5,
                title = "Pictures of foxes",
                cover = "file:///android_asset/pictures/image_05.webp",
                baseUrl = "https://randomfox.ca/floof/",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 6,
                title = "Pictures of zoo animals",
                cover = "file:///android_asset/pictures/image_06.webp",
                baseUrl = "https://zoo-animal-api.herokuapp.com/animals/rand",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 7,
                title = "Pictures of coffee",
                cover = "file:///android_asset/pictures/image_07.webp",
                baseUrl = "https://coffee.alexflipnote.dev/random.json",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 8,
                title = "Pictures of food dishes",
                cover = "file:///android_asset/pictures/image_08.webp",
                baseUrl = "https://foodish-api.herokuapp.com/api",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 9,
                title = "Neko images, funny GIFs & more",
                cover = "file:///android_asset/pictures/image_09.webp",
                baseUrl = "https://api.catboys.com/img",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 11,
                title = "Biriyani images",
                cover = "file:///android_asset/pictures/image_11.webp",
                baseUrl = "https://biriyani.anoram.com/get",
                type = RandomType.PICTURE
            ),
            ListModel(
                id = 12,
                title = "Images from Unsplash",
                cover = "file:///android_asset/pictures/image_12.webp",
                baseUrl = "https://picsum.photos/1080",
                isImageUrl = true,
                type = RandomType.PICTURE
            )
        )

    private val jokesList
        get() = listOf(
            ListModel(
                id = 1,
                title = "Dad jokes",
                cover = "file:///android_asset/jokes/image_01.webp",
                baseUrl = "https://icanhazdadjoke.com/",
                type = RandomType.JOKE
            ),
            ListModel(
                id = 2,
                title = "Chuck Norris jokes",
                cover = "file:///android_asset/jokes/image_02.webp",
                baseUrl = "https://api.chucknorris.io/jokes/random",
                type = RandomType.JOKE
            ),
            ListModel(
                id = 3,
                title = "Yo Momma jokes",
                cover = "file:///android_asset/jokes/image_03.webp",
                baseUrl = "https://yomomma-api.herokuapp.com/jokes",
                type = RandomType.JOKE
            ),
            ListModel(
                id = 4,
                title = "Programming jokes",
                cover = "file:///android_asset/jokes/image_04.webp",
                baseUrl = "https://v2.jokeapi.dev/joke/Any",
                type = RandomType.JOKE
            ),
            ListModel(
                id = 5,
                title = "Dev jokes",
                cover = "file:///android_asset/jokes/image_05.webp",
                baseUrl = "https://backend-omega-seven.vercel.app/api/getjoke",
                isListUrl = true,
                type = RandomType.JOKE
            )
        )

    private val factsList
        get() = listOf(
            ListModel(
                id = 1,
                title = "Facts of cats",
                cover = "file:///android_asset/facts/image_01.webp",
                baseUrl = "https://freefacts.herokuapp.com/facts/random",
                isListUrl = true,
                type = RandomType.FACT
            ),
            ListModel(
                id = 2,
                title = "Facts of dogs",
                cover = "file:///android_asset/facts/image_02.webp",
                baseUrl = "https://dog-api.kinduff.com/api/facts?number=1",
                type = RandomType.FACT
            ),
            ListModel(
                id = 3,
                title = "Anime facts",
                cover = "file:///android_asset/facts/image_03.webp",
                baseUrl = "https://freefacts.herokuapp.com/facts/random",
                isListUrl = true,
                type = RandomType.FACT
            ),
            ListModel(
                id = 4,
                title = "Useless, but true facts",
                cover = "file:///android_asset/facts/image_04.webp",
                baseUrl = "https://uselessfacts.jsph.pl/random.json?language=en",
                type = RandomType.FACT
            ),
            ListModel(
                id = 5,
                title = "Fun facts",
                cover = "file:///android_asset/facts/image_05.webp",
                baseUrl = "https://asli-fun-fact-api.herokuapp.com/",
                type = RandomType.FACT
            ),
            ListModel(
                id = 6,
                title = "Facts about numbers",
                cover = "file:///android_asset/facts/image_06.webp",
                baseUrl = "http://numbersapi.com/random/${numberType}?json",
                type = RandomType.FACT
            ),
            ListModel(
                id = 7,
                title = "Chuck Norris facts",
                cover = "file:///android_asset/facts/image_07.webp",
                baseUrl = "https://api.chucknorris.io/jokes/random",
                type = RandomType.FACT
            ),
            ListModel(
                id = 8,
                title = "Knowledge facts",
                cover = "file:///android_asset/facts/image_08.webp",
                baseUrl = "https://freefacts.herokuapp.com/facts/random",
                isListUrl = true,
                type = RandomType.FACT
            ),
            ListModel(
                id = 9,
                title = "Random facts",
                cover = "file:///android_asset/facts/image_09.webp",
                baseUrl = "https://freefacts.herokuapp.com/facts/random",
                isListUrl = true,
                type = RandomType.FACT
            )
        )

    private val numberType get() = listOf("trivia", "math", "date", "year").random()

    private val quotesList
        get() = listOf(
            ListModel(
                id = 1,
                title = "Anime quotes",
                cover = "file:///android_asset/quotes/image_01.webp",
                baseUrl = "https://animechan.vercel.app/api/random",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 2,
                title = "Inspirational quotes",
                cover = "file:///android_asset/quotes/image_02.webp",
                baseUrl = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 3,
                title = "Motivational quotes",
                cover = "file:///android_asset/quotes/image_03.webp",
                baseUrl = "https://api.goprogram.ai/inspiration",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 4,
                title = "Kanye West quotes",
                cover = "file:///android_asset/quotes/image_04.webp",
                baseUrl = "https://api.kanye.rest/",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 5,
                title = "Kimi Räikkönen quotes",
                cover = "file:///android_asset/quotes/image_05.webp",
                baseUrl = "https://kimiquotes.herokuapp.com/quote",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 6,
                title = "Ron Swanson quotes",
                cover = "file:///android_asset/quotes/image_06.webp",
                baseUrl = "http://ron-swanson-quotes.herokuapp.com/v2/quotes",
                isStringListUrl = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 7,
                title = "Game of Thrones quotes",
                cover = "file:///android_asset/quotes/image_07.webp",
                baseUrl = "https://api.gameofthronesquotes.xyz/v1/random",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 8,
                title = "Breaking Bad quotes",
                cover = "file:///android_asset/quotes/image_08.webp",
                baseUrl = "https://api.breakingbadquotes.xyz/v1/quotes",
                isListUrl = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 9,
                title = "Stranger Things quotes",
                cover = "file:///android_asset/quotes/image_09.webp",
                baseUrl = "https://strangerthings-quotes.vercel.app/api/quotes",
                isListUrl = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 10,
                title = "Friends quotes",
                cover = "file:///android_asset/quotes/image_10.webp",
                baseUrl = "https://friends-quotes-api.herokuapp.com/quotes/random",
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 11,
                title = "Lucifer quotes",
                cover = "file:///android_asset/quotes/image_11.webp",
                baseUrl = "https://lucifer-quotes.vercel.app/api/quotes",
                isListUrl = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 12,
                title = "Zen quotes",
                cover = "file:///android_asset/quotes/image_12.webp",
                baseUrl = "https://zenquotes.io/api/random",
                isListUrl = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 13,
                title = "Programming quotes",
                cover = "file:///android_asset/quotes/image_13.webp",
                baseUrl = "https://programming-quotes-api.herokuapp.com/quotes/random",
                isProgramming = true,
                type = RandomType.QUOTE
            ),
            ListModel(
                id = 14,
                title = "Code quotes",
                cover = "file:///android_asset/quotes/image_14.webp",
                baseUrl = "https://nodejs-quoteapp.herokuapp.com/quote",
                isProgramming = true,
                type = RandomType.QUOTE
            )
        )

    private val moreList
        get() = listOf(
            ListModel(
                id = 1,
                title = "Activities",
                baseUrl = "https://www.boredapi.com/api/activity/",
                type = RandomType.MORE
            ),
            ListModel(
                id = 2,
                title = "Excuses for various situations",
                baseUrl = "https://excuser.herokuapp.com/v1/excuse",
                isListUrl = true,
                type = RandomType.MORE
            ),
            ListModel(
                id = 3,
                title = "Advice slips",
                baseUrl = "https://api.adviceslip.com/advice",
                type = RandomType.MORE
            )
        )
}