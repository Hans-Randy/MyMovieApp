package com.example.mymovieapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieapp.ui.theme.MyMovieAppTheme

class MovieEntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieEntryScreen()
                }
            }
        }
    }
}

@Composable
fun MovieEntryScreen() {
    var movieTitle by remember { mutableStateOf("") }
    var directorName by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf("Action") }
    var yearReleased by remember { mutableStateOf("") }
    var lengthInMins by remember { mutableStateOf("") }
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var expandedGenre by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val genres = listOf("Action", "Thriller", "Family", "Classic")

    val segmentedButtonHeight = 36.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Movie Entry",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Movie Title Input
        TextField(
            value = movieTitle,
            onValueChange = { movieTitle = it },
            label = { Text("Movie Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Director Name Input
        TextField(
            value = directorName,
            onValueChange = { directorName = it },
            label = { Text("Director Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Genre Dropdown
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Button(
                onClick = { expandedGenre = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(segmentedButtonHeight)
            ) {
                Text("Genre: $selectedGenre")
            }
            DropdownMenu(
                expanded = expandedGenre,
                onDismissRequest = { expandedGenre = false }
            ) {
                genres.forEach { genre ->
                    DropdownMenuItem(
                        text = { Text(genre) },
                        onClick = {
                            selectedGenre = genre
                            expandedGenre = false
                        }
                    )
                }
            }
        }

        // Year Released Input
        TextField(
            value = yearReleased,
            onValueChange = { yearReleased = it },
            label = { Text("Year Released") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        // Length in Minutes Input
        TextField(
            value = lengthInMins,
            onValueChange = { lengthInMins = it },
            label = { Text("Length (minutes)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        // Add Movie Button
        Button(
            onClick = {
                if (movieTitle.isNotBlank() && directorName.isNotBlank() &&
                    yearReleased.isNotBlank() && lengthInMins.isNotBlank()
                ) {
                    val newMovie = Movie(
                        title = movieTitle,
                        director = directorName,
                        genre = selectedGenre,
                        yearReleased = yearReleased.toInt(),
                        lengthInMins = lengthInMins.toInt()
                    )
                    movieList = movieList + newMovie

                    // Show Toast/Snackbar with movie details
                    val message = "Movie Added!\n" +
                            "Title: $movieTitle\n" +
                            "Director: $directorName\n" +
                            "Genre: $selectedGenre\n" +
                            "Year: $yearReleased\n" +
                            "Length: $lengthInMins mins"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

                    // Clear fields
                    movieTitle = ""
                    directorName = ""
                    yearReleased = ""
                    lengthInMins = ""
                    selectedGenre = "Action"
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(bottom = 16.dp)
        ) {
            Text("Add Movie")
        }

        // Movie List
        if (movieList.isNotEmpty()) {
            Text(
                text = "Movies (${movieList.size})",
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movieList) { movie ->
                    MovieCard(movie)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No movies added yet",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = movie.title,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Director: ${movie.director}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Genre: ${movie.genre}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Year: ${movie.yearReleased}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Length: ${movie.lengthInMins} mins",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
