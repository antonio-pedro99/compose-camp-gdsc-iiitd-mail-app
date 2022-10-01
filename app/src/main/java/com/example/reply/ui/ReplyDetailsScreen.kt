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

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * Component that displays details screen
 */
@Composable
fun ReplyDetailsScreen(
    replyUiState: ReplyUiState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    isFullScreen: Boolean = false
) {

    //Add BackButton
    BackHandler {
        onBackPressed()
    }

    LazyColumn(
        modifier = modifier
            .testTag(stringResource(R.string.details_screen))
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = 24.dp)
    ) {
        item {
            if (isFullScreen) {
                //ReplyDetailsScreenTopBar(onBackPressed, replyUiState)
            }
//            ReplyEmailDetailsCard(
//                email = replyUiState.currentSelectedEmail,
//                mailboxType = replyUiState.currentMailbox,
//                isFullScreen = isFullScreen,
//                modifier = if (isFullScreen)
//                    Modifier.padding(horizontal = 16.dp)
//                else
//                    Modifier.padding(end = 16.dp)
//            )
        }
    }
}

// 13. add Reply Details Screen To Bar



//12. Email Details Card


//11. Details Screen Button bar


//9. details Screen Header



//10. Add ActionButton

