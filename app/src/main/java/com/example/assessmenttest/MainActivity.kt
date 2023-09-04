package com.example.assessmenttest

import EmailViewModel
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.assessmenttest.compose.AssessmentTestApp
import com.example.assessmenttest.module.ImageListViewModel
import com.example.assessmenttest.ui.theme.AssessmentTestTheme

class MainActivity : ComponentActivity() {
    private val emailViewModel: EmailViewModel by viewModels<EmailViewModel>()
    private val viewModel: ImageListViewModel by viewModels<ImageListViewModel>()

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        emailViewModel.createEmailIntent.observe(this) { intent ->
            // Get the subject from the email intent

            var image = intent.getStringExtra("image")
            // Download the image from the URL
            val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadRequest =
                DownloadManager.Request(Uri.parse(image))
            downloadRequest.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                "image.png"
            )
            val downloadId = downloadManager.enqueue(downloadRequest)

            // Wait for the download to finish
            val query = DownloadManager.Query()
            query.setFilterById(downloadId)
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    // Get the URI of the downloaded image
                    val imageUri = downloadManager.getUriForDownloadedFile(downloadId)

                    // Attach the image to the email intent
                    intent.putExtra(Intent.EXTRA_STREAM, imageUri)

                } else {
                    // Handle download failure
                }
            }
            cursor.close()
            try {
                startActivity(Intent.createChooser(intent, "Send email"))
            } catch (ex: ActivityNotFoundException) {
                // handle edge case where no email client is installed
                Toast.makeText(this, "Email client not find!${ex.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        setContent {
            AssessmentTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AssessmentTestApp(viewModel, emailViewModel)
                }
            }
        }
    }
}