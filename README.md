Reply App - Solution Code
=================================
Starter Code for Android Basic with Compose sample App - Replay


## Design & Screenshots

<img src="screenshots/reply.gif"/>

<img src="screenshots/medium_and_large_display.png">

Introduction
------------
The Reply app is a simple email client that display various categories of your
inbox. This app is used to illustrate the concept of adaptive layout.

Pre-requisites
--------------

* Experience with Kotlin syntax.
* How to create and run a project in Android Studio.
* How to create composable functions
* How to create compose navigation

Getting Started
---------------

1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

Implementing
------------
#### 1. Build a Preview: MainActivity.kt
```
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReplyAppCompactPreview() {
    ReplyTheme {
       ReplyApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
```

#### 2. Setup App Content: MainActivity.kt
```
ReplyApp(
    windowSize = windowSize.widthSizeClass,
)
```

#### 3. Load Profile Image: ReplyHopmeContent.kt
```
@Composable
fun ReplyProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painterResource(drawableResource),
        contentDescription = description,
    )
}
```

#### 4. Load Logo: ReplyHopmeContent.kt
```
@Composable
fun ReplyLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}
```

#### 5. Design HomeTabBar: ReplyHopmeContent.kt
```
@Composable
private fun ReplyHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        ReplyLogo(
            modifier = Modifier
                .size(64.dp)
                .padding(start = 4.dp)
        )
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.userAccount.avatar,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = 8.dp)
                .size(48.dp)
        )
    }
}
```

#### 6. Design Reply Email Item Header: ReplyHopmeContent.kt
```
@Composable
private fun ReplyEmailItemHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = email.sender.fullName,
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = email.sender.firstName,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = email.createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
```

#### 7. Design Email Item List: ReplyHopmeContent.kt
```
@Composable
fun ReplyEmailListItem(
    email: Email,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ReplyEmailItemHeader(email)
            Text(
                text = email.subject,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = email.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
```

#### 8. Build List of Only Content: ReplyHopmeContent.kt
```
@Composable
fun ReplyListOnlyContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails


      //list Emails with LazyColumn
        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            ReplyHomeTopBar(modifier = Modifier.fillMaxWidth())
        }
        items(emails, key = { email -> email.id }) { email ->
            ReplyEmailListItem(
                email = email,
                onCardClick = {
                    onEmailCardPressed(email)
                }
            )
        }
    }
}
```

#### 9. Details Screen Header: ReplyDetailsScreen.kt
```
@Composable
private fun DetailsScreenHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = email.sender.fullName,
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = email.sender.firstName,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = email.createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
```

#### 10. Build Action Button: ReplyDetailsScreen.kt
```
@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false
) {
    Button(
        onClick = { onButtonClicked(text) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.inverseOnSurface
            else MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            text = text,
            color =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.onSurfaceVariant
            else MaterialTheme.colorScheme.onError
        )
    }
}
```
#### 11. Details Screen Button Bar: ReplyDetailsScreen.kt
```
@Composable
private fun DetailsScreenButtonBar(
    mailboxType: MailboxType,
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (mailboxType) {
        MailboxType.Drafts ->
            //add Action Button for Draft Messages
           
        MailboxType.Spam ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                //add Action Button for Spam Messages
              
              
            }
        MailboxType.Sent, MailboxType.Inbox ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                //add Action Button for Sent Messages
               
            }
    }
}
```
#### 12. Build Email Details Card: ReplyDetailsScreen.kt
```
@Composable
private fun ReplyEmailDetailsCard(
    email: Email,
    mailboxType: MailboxType,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            //add Details Screen Header
            DetailsScreenHeader(email)
            if (!isFullScreen) {
                Text(
                    text = email.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }
            Text(
                text = email.body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            //add Details ScreenButtonBar
            DetailsScreenButtonBar(mailboxType, displayToast)
        }
    }
}
```
#### 13. Build Replay Details Screen Top Bar: ReplyDetailsScreen.kt
```
@Composable
private fun ReplyDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    replyUiState: ReplyUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = replyUiState.currentSelectedEmail.subject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
```
#### 14. Build a Preview: ReplyDetailsScreen.kt
```
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReplyAppCompactPreview() {
    ReplyTheme {
       ReplyApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
```

Creditis and License
--------------------
We used this app for educational Purpose only. All rights are served by Google Developer Team.
## License
```
Copyright 2022 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[compose]: https://developer.android.com/jetpack/compose
[reply]: https://m3.material.io/foundations/adaptive-design/overview
[materialtheming]: https://m3.material.io/styles/color/dynamic-color/overview
