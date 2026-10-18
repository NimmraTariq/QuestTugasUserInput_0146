package com.example.questtugasuserinput_0146

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun PagerDots(modifier: Modifier = Modifier) {
    val dotColors = listOf(
        colorResource(id = R.color.dot_active),
        colorResource(id = R.color.dot_inactive),
        colorResource(id = R.color.dot_inactive),
        colorResource(id = R.color.dot_inactive)
    )

    Row(
        modifier = modifier.padding(dimensionResource(id = R.dimen.dots_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dot_spacing))
    ) {
        dotColors.forEach { dotColor ->
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.dot_size))
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.header_height))
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_image),
            contentDescription = stringResource(id = R.string.cd_header_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        PagerDots(modifier = Modifier.align(Alignment.TopStart))
    }
}

@Composable
fun FormTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.form_title),
            color = colorResource(id = R.color.title_purple),
            fontSize = dimensionResource(id = R.dimen.title_size).value.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.title_spacing)))
        Text(
            text = stringResource(id = R.string.form_subtitle),
            color = colorResource(id = R.color.subtitle_gray),
            fontSize = dimensionResource(id = R.dimen.subtitle_size).value.sp
        )
    }
}

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.field_corner_radius)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.title_purple),
            unfocusedBorderColor = colorResource(id = R.color.field_border),
            focusedTextColor = colorResource(id = R.color.field_text),
            unfocusedTextColor = colorResource(id = R.color.field_text)
        ),
        modifier = modifier
    )
}

@Composable
fun NameFields(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.field_spacing))
    ) {
        FormTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            label = stringResource(id = R.string.label_first_name),
            modifier = Modifier.weight(1f)
        )
        FormTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = stringResource(id = R.string.label_last_name),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FormTextField(
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(id = R.string.label_email),
        modifier = modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateField(
    birthDate: String,
    onBirthDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val dateFormat = SimpleDateFormat(stringResource(id = R.string.date_format), Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone(stringResource(id = R.string.date_time_zone))

    Box(modifier = modifier) {
        FormTextField(
            value = birthDate,
            onValueChange = {},
            label = stringResource(id = R.string.label_birth_date),
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.cd_calendar),
                    tint = colorResource(id = R.color.field_text),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size))
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { showDialog = true }
        )
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            onBirthDateChange(dateFormat.format(Date(millis)))
                        }
                        showDialog = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.dialog_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.dialog_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun ExperienceDropdown(
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val options = stringArrayResource(id = R.array.experience_options)

    Box(modifier = modifier) {
        FormTextField(
            value = selected,
            onValueChange = {},
            label = stringResource(id = R.string.label_experience),
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                    contentDescription = stringResource(id = R.string.cd_dropdown),
                    tint = colorResource(id = R.color.field_text)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}