/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.reply.ui

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.local.LocalAccountsDataProvider

/**
 * Component that displays a single pane of list of emails
 */

//8. build the list only of Emails


/**
 * Component that displays two panes of list of emails and email details
 */
@Composable
fun ReplyListAndDetailContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 20.dp)
        ) {
            items(emails, key = { email -> email.id }) { email ->
//                ReplyEmailListItem(
//                    email = email,
//                    onCardClick = {
//                        onEmailCardPressed(email)
//                    }
//                )
            }
        }
        val activity = LocalContext.current as Activity


        ReplyDetailsScreen(
            replyUiState = replyUiState,
            modifier = Modifier.weight(1f),
            onBackPressed = { activity.finish() }
        )
    }
}

/**
 * Component that displays a single email list item
 */


// 7. Email List Item Design


// 6. Reply Email Header


/**
 * Component that displays profile image
 */



//3. Load Profile Image


/**
 * Component that displays Reply logo
 */

// 4. Add Reply Logo Desing


/**
 * Component that displays top bar.
 * This is used when there is no navigation drawer
 */

// 5. Design HomeTopBar

