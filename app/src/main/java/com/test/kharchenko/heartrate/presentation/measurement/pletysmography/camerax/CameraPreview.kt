package com.test.kharchenko.heartrate.presentation.measurement.pletysmography.camerax

import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat

@Composable
fun CameraPreview(
    modifier: Modifier,
    onFrameCaptured: (ImageProxy) -> Unit,
    onCameraControl: (camera: Camera, cameraControl: CameraControl) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        modifier = modifier
            .then(modifier),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val preview = androidx.camera.core.Preview.Builder().build()
            val imageCapture =
                ImageCapture.Builder().build()
            val imageAnalyzer = ImageAnalysis.Builder()
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888).build().also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(ctx)) { imageProxy ->
                        onFrameCaptured(imageProxy)
                        imageProxy.close()
                    }
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                cameraProvider.unbindAll()
                val camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture,
                    imageAnalyzer
                )

                onCameraControl(camera, camera.cameraControl)

                preview.setSurfaceProvider(previewView.surfaceProvider)
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        }
    )
}
