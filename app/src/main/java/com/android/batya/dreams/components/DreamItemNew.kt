import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.batya.dreams.components.ChipGroup
import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Tag
import com.android.batya.dreams.ui.theme.BorderColor
import com.android.batya.dreams.ui.theme.CardBackgroundColor
import com.android.batya.dreams.ui.theme.DateTimeTextColor
import com.android.batya.dreams.R
import com.android.batya.dreams.utils.getShorFormattedDate
import kotlin.random.Random

@Composable
fun DreamItemNew(
    dream: Dream,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = CardBackgroundColor,
        //if(dream.isLucid) CardLucidBackgroundColor
        //else CardNonLucidBackgroundColor,
        elevation = 0.dp,
        border = BorderStroke(1.dp, BorderColor),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 17.dp,
                    end = 17.dp,
                    top = 18.dp,
                    bottom = 18.dp
                )

        ) {
            //Divider(modifier = Modifier.padding(6.dp), color = BorderColor)
            //Spacer(modifier = Modifier.height(11.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    modifier = Modifier
                        .width(165.dp),
                    text = dream.id,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        if(Random.nextInt(10) > 2) {
                            Image(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(
                                    id =
                                    when(Random.nextInt(4)) {
                                        0 -> { R.drawable.ic_mood_horror }
                                        1 -> { R.drawable.ic_mood_bad }
                                        2 -> { R.drawable.ic_mood_normal }
                                        3 -> { R.drawable.ic_mood_good }
                                        else -> { R.drawable.ic_mood_excellent }
                                    }
                                ),
                                contentDescription = "Mood Icon",
                            )
                        }

                        if(Random.nextInt(2) == 0) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.ic_lucid),
                                contentDescription = "Mood Icon",
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            modifier = Modifier,
                            text = getShorFormattedDate(dream.date),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DateTimeTextColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = dream.description,
                fontSize = 11.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            if (dream.tags != emptyList<Tag>()) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxSize().padding(start = 1.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    ChipGroup(tags = dream.tags)
                }
            }
        }
    }
}
