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

@Composable
fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.checkbox_checked),
                uncheckedColor = colorResource(id = R.color.checkbox_unchecked)
            )
        )
        Text(
            text = label,
            color = colorResource(id = R.color.field_text),
            fontSize = dimensionResource(id = R.dimen.skill_text_size).value.sp
        )
    }
}

@Composable
fun SkillsSection(
    androidChecked: Boolean,
    onAndroidChange: (Boolean) -> Unit,
    uiuxChecked: Boolean,
    onUiuxChange: (Boolean) -> Unit,
    webChecked: Boolean,
    onWebChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.label_skills),
            color = colorResource(id = R.color.field_text),
            fontSize = dimensionResource(id = R.dimen.skills_label_size).value.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SkillCheckbox(
                label = stringResource(id = R.string.skill_android),
                checked = androidChecked,
                onCheckedChange = onAndroidChange
            )
            SkillCheckbox(
                label = stringResource(id = R.string.skill_uiux),
                checked = uiuxChecked,
                onCheckedChange = onUiuxChange
            )
            SkillCheckbox(
                label = stringResource(id = R.string.skill_web),
                checked = webChecked,
                onCheckedChange = onWebChange
            )
        }
    }
}

@Composable
fun ProceedButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.button_enabled),
            contentColor = colorResource(id = R.color.button_text),
            disabledContainerColor = colorResource(id = R.color.button_disabled),
            disabledContentColor = colorResource(id = R.color.button_text_disabled)
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.button_height))
    ) {
        Text(text = stringResource(id = R.string.button_proceed))
    }
}

@Composable
fun ResultBox(
    firstName: String,
    lastName: String,
    email: String,
    birthDate: String,
    experience: String,
    skills: String,
    modifier: Modifier = Modifier
) {
    val textColor = colorResource(id = R.color.result_text)
    val textSize = dimensionResource(id = R.dimen.result_text_size).value.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.result_corner_radius)))
            .background(colorResource(id = R.color.result_background))
            .padding(dimensionResource(id = R.dimen.result_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.result_spacing))
    ) {
        Text(
            text = stringResource(id = R.string.result_title),
            color = textColor,
            fontSize = dimensionResource(id = R.dimen.result_title_size).value.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.result_name, firstName, lastName),
            color = textColor,
            fontSize = textSize
        )
        Text(
            text = stringResource(id = R.string.result_email, email),
            color = textColor,
            fontSize = textSize
        )
        Text(
            text = stringResource(id = R.string.result_birth_date, birthDate),
            color = textColor,
            fontSize = textSize
        )
        Text(
            text = stringResource(id = R.string.result_experience, experience),
            color = textColor,
            fontSize = textSize
        )
        Text(
            text = stringResource(id = R.string.result_skills, skills),
            color = textColor,
            fontSize = textSize
        )
    }
}

@Composable
fun BiodataForm(modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var androidChecked by remember { mutableStateOf(false) }
    var uiuxChecked by remember { mutableStateOf(false) }
    var webChecked by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }

    val skillAndroid = stringResource(id = R.string.skill_android)
    val skillUiux = stringResource(id = R.string.skill_uiux)
    val skillWeb = stringResource(id = R.string.skill_web)
    val separator = stringResource(id = R.string.skills_separator)

    val selectedSkills = listOfNotNull(
        skillAndroid.takeIf { androidChecked },
        skillUiux.takeIf { uiuxChecked },
        skillWeb.takeIf { webChecked }
    )
    val allFilled = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            selectedSkills.isNotEmpty()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(id = R.dimen.card_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.section_spacing))
    ) {
        FormTitle()
        NameFields(
            firstName = firstName,
            onFirstNameChange = { firstName = it },
            lastName = lastName,
            onLastNameChange = { lastName = it }
        )
        EmailField(
            email = email,
            onEmailChange = { email = it }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.field_spacing))
        ) {
            BirthDateField(
                birthDate = birthDate,
                onBirthDateChange = { birthDate = it },
                modifier = Modifier.weight(1f)
            )
            ExperienceDropdown(
                selected = experience,
                onSelected = { experience = it },
                modifier = Modifier.weight(1f)
            )
        }
        SkillsSection(
            androidChecked = androidChecked,
            onAndroidChange = { androidChecked = it },
            uiuxChecked = uiuxChecked,
            onUiuxChange = { uiuxChecked = it },
            webChecked = webChecked,
            onWebChange = { webChecked = it }
        )
        ProceedButton(
            enabled = allFilled,
            onClick = { submitted = true }
        )
        if (submitted && allFilled) {
            ResultBox(
                firstName = firstName,
                lastName = lastName,
                email = email,
                birthDate = birthDate,
                experience = experience,
                skills = selectedSkills.joinToString(separator)
            )
        }
    }
}

@Composable
fun BiodataScreen(modifier: Modifier = Modifier) {
    val cardShape = RoundedCornerShape(
        topStart = dimensionResource(id = R.dimen.card_corner_radius),
        topEnd = dimensionResource(id = R.dimen.card_corner_radius)
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderImage(modifier = Modifier.align(Alignment.TopCenter))
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = dimensionResource(id = R.dimen.card_top_margin))
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.card_elevation),
                        shape = cardShape
                    )
                    .clip(cardShape)
                    .background(colorResource(id = R.color.card_background))
            ) {
                BiodataForm()
            }
        }
    }
}