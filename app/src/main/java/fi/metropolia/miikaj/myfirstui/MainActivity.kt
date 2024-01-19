package fi.metropolia.miikaj.myfirstui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.border
import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import fi.metropolia.miikaj.myfirstui.ui.theme.AppTheme
import fi.metropolia.miikaj.myfirstui.ui.theme.md_theme_dark_secondary
import fi.metropolia.miikaj.myfirstui.ui.theme.md_theme_light_tertiary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Conversation(SampleData.conversationSample)
            AppTheme {
                Surface(modifier = Modifier) {
                    MessageCard(Message("Android", "Jetpack Compose. What a lovely day it will be...after tomorrow :)"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Column(
        modifier = Modifier
            .background(md_theme_light_tertiary)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Morjesta Pöytään", color = md_theme_dark_secondary)
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
                    ,
                colors = buttonColors(md_theme_light_tertiary)
            ){
                Icon(
                    imageVector = Icons.Default.Menu,
                    modifier = Modifier
                        .size(30.dp)
                , contentDescription = null)
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                ,
                colors = buttonColors(md_theme_light_tertiary)
            ){
                Icon(
                    imageVector = Icons.Default.Favorite,
                    modifier = Modifier
                        .size(30.dp)
                    , contentDescription = null)
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.vaasi1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.tursas),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))

                // We keep track if the message is expanded or not in this
                // variable
                var isExpanded by remember { mutableStateOf(false) }
                // surfaceColor will be updated gradually from one color to the other
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    label = "",
                )
                // We toggle the isExpanded variable when we click on this Column
                Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 1.dp,
                        // surfaceColor color will be changing gradually from primary to surface
                        color = surfaceColor,
                        // animateContentSize will change the Surface size gradually
                        modifier = Modifier
                            .animateContentSize()
                            .padding(1.dp)
                    ) {
                        Text(
                            text = msg.body,
                            modifier = Modifier.padding(all = 4.dp),
                            // If the message is expanded, we display all its content
                            // otherwise we only display the first line
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
            ) {
                Text("Send :)")
            }
        }
    }
}

/*@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}*/

@Preview
/*@Composable
fun PreviewConversation() {
    AppTheme {
        Conversation(SampleData.conversationSample)
    }
}*/

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    AppTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great! I would still like to use React :(")
            )
        }
    }
}