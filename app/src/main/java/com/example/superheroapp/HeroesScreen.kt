package com.example.superheroapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroapp.data.HeroesData.heroes
import com.example.superheroapp.model.Hero
import com.example.superheroapp.ui.theme.SuperheroAppTheme

@Composable
fun HeroItemText(nameRes: Int, descriptionRes: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(end = dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(id = nameRes),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroItemImage(imageRes: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
        )
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .height(72.dp)
        ) {
            HeroItemText(
                nameRes = hero.nameRes,
                descriptionRes = hero.descriptionRes,
                modifier = Modifier
                    .weight(1f)
            )
            HeroItemImage(
                imageRes = hero.imageRes
            )
        }
    }
}

@Composable
fun HeroList(heroes: List<Hero>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(heroes) { hero ->
            HeroItem(
                hero = hero,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroAppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier)
}

@Composable
fun HeroApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { HeroAppTopBar() }
    ) { innerPadding ->
        HeroList(heroes = heroes, modifier.padding(innerPadding))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HeroItemPreview(){
    SuperheroAppTheme {
        HeroApp()
    }
}