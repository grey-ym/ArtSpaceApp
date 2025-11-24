package com.ym.grey.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ym.grey.artspace.data.Art
import com.ym.grey.artspace.ui.theme.ArtSpaceTheme
import com.ym.grey.artspace.ui.theme.ImgInfoBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold { paddingValues ->
                    ArtSpaceApp(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var pageNumber by remember { mutableIntStateOf(1) }
    val art = findArt(pageNumber)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 30.dp, vertical = 40.dp)
    ) {

        ArtImage(art.image)
        ArtInfo(modifier = Modifier.padding(top = 40.dp).background(color = ImgInfoBg), art)

        // Navigation buttons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 10.dp)
        ) {
            NavigationButton(
                modifier = Modifier,
                text = R.string.previous,
                isEnabled = pageNumber > 1
            ) {
                if (pageNumber > 1) pageNumber--
            }
            NavigationButton(
                modifier = Modifier,
                text = if (pageNumber < 20) R.string.next else R.string.finish
            ) {
                if (pageNumber >= 20) pageNumber = 1 else pageNumber++
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun ArtImagePreview() {
    ArtImage(
        R.drawable.ic_launcher_background,
        modifier = Modifier.padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ArtInfoPreview() {
    ArtInfo(
        modifier = Modifier.padding(30.dp).background(color = ImgInfoBg),
        Art(
            image = R.drawable.img_1,
            title = R.string.img_1_title,
            artist = R.string.img_1_artist,
            year = R.string.img_1_year
        )
    )
}

@Composable
fun ArtImage(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorResource(id = R.color.white),
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 7.dp
    ) {
        Image(
            painter = painterResource(imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.9f)
                .padding(36.dp)
        )
    }
}

@Composable
fun ArtInfo(
    modifier: Modifier = Modifier,
    artData: Art
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraLight,
                fontSize = 26.sp,
                text = stringResource(artData.title)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(artData.artist)
                )
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.ExtraLight,
                    text = " (${stringResource(artData.year)})"
                )
            }
        }
    }
}

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = isEnabled,
        modifier = modifier.width(120.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = text)
        )
    }
}

// Returns image data
private fun findArt(page: Int): Art {
    return when(page) {
        1 -> Art(image = R.drawable.img_1, title = R.string.img_1_title, artist = R.string.img_1_artist, year = R.string.img_1_year)
        2 -> Art(image = R.drawable.img_2, title = R.string.img_2_title, artist = R.string.img_2_artist, year = R.string.img_2_year)
        3 -> Art(image = R.drawable.img_3, title = R.string.img_3_title, artist = R.string.img_3_artist, year = R.string.img_3_year)
        4 -> Art(image = R.drawable.img_4, title = R.string.img_4_title, artist = R.string.img_4_artist, year = R.string.img_4_year)
        5 -> Art(image = R.drawable.img_5, title = R.string.img_5_title, artist = R.string.img_5_artist, year = R.string.img_5_year)
        6 -> Art(image = R.drawable.img_6, title = R.string.img_6_title, artist = R.string.img_6_artist, year = R.string.img_6_year)
        7 -> Art(image = R.drawable.img_7, title = R.string.img_7_title, artist = R.string.img_7_artist, year = R.string.img_7_year)
        8 -> Art(image = R.drawable.img_8, title = R.string.img_8_title, artist = R.string.img_8_artist, year = R.string.img_8_year)
        9 -> Art(image = R.drawable.img_9, title = R.string.img_9_title, artist = R.string.img_9_artist, year = R.string.img_9_year)
        10 -> Art(image = R.drawable.img_10, title = R.string.img_10_title, artist = R.string.img_10_artist, year = R.string.img_10_year)
        11 -> Art(image = R.drawable.img_11, title = R.string.img_11_title, artist = R.string.img_11_artist, year = R.string.img_11_year)
        12 -> Art(image = R.drawable.img_12, title = R.string.img_12_title, artist = R.string.img_12_artist, year = R.string.img_12_year)
        13 -> Art(image = R.drawable.img_13, title = R.string.img_13_title, artist = R.string.img_13_artist, year = R.string.img_13_year)
        14 -> Art(image = R.drawable.img_14, title = R.string.img_14_title, artist = R.string.img_14_artist, year = R.string.img_14_year)
        15 -> Art(image = R.drawable.img_15, title = R.string.img_15_title, artist = R.string.img_15_artist, year = R.string.img_15_year)
        16 -> Art(image = R.drawable.img_16, title = R.string.img_16_title, artist = R.string.img_16_artist, year = R.string.img_16_year)
        17 -> Art(image = R.drawable.img_17, title = R.string.img_17_title, artist = R.string.img_17_artist, year = R.string.img_17_year)
        18 -> Art(image = R.drawable.img_18, title = R.string.img_18_title, artist = R.string.img_18_artist, year = R.string.img_18_year)
        19 -> Art(image = R.drawable.img_19, title = R.string.img_19_title, artist = R.string.img_19_artist, year = R.string.img_19_year)
        20 -> Art(image = R.drawable.img_20, title = R.string.img_20_title, artist = R.string.img_20_artist, year = R.string.img_20_year)
        else -> Art(image = R.drawable.img_1, title = R.string.img_1_title, artist = R.string.img_1_artist, year = R.string.img_1_year)
    }
}