package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils

sealed class State<T> {
    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    class Failed<T> : State<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed() = Failed<T>()
    }
}
