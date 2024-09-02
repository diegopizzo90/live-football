package com.diegopizzo.design.components.toolbar

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diegopizzo.design.components.text.LFHeadingLarge
import com.diegopizzo.design.theme.LFTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LFTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            LFHeadingLarge(
                text = title,
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

@Preview("Default", "LFTopAppBar")
@Preview("Dark theme", "LFTopAppBar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LFTopAppBarPreview() {
    LFTheme {
        LFTopAppBar("LFTopAppBar")
    }
}
