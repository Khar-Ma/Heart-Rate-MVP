import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.test.kharchenko.heartrate.ui.theme.fixedColors

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
) {
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalDots) { index ->
            val width: Dp by animateDpAsState(targetValue = if (index == selectedIndex) 44.dp else 14.dp)
            val height: Dp by animateDpAsState(targetValue = 14.dp)
            val color: Color by animateColorAsState(targetValue = if (index == selectedIndex) MaterialTheme.fixedColors.primaryColor else MaterialTheme.fixedColors.gray)

            Box(
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .background(
                        color,
                        shape = if (index == selectedIndex) RoundedCornerShape(50) else CircleShape
                    )
            )
        }
    }
}
