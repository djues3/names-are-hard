import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.djues3.names_are_hard.App
import com.djues3.names_are_hard.ui.theme.Theme


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        Theme {
            App()
        }
    }
}