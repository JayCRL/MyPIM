package com.example.mypim.ui.screens.reminders

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderScreen(
    navController: NavController,
    viewModel: AddReminderViewModel = hiltViewModel()
) {
    val title by viewModel.title.collectAsState()
    val type by viewModel.type.collectAsState()
    val trigger by viewModel.trigger.collectAsState()
    val nextDueDate by viewModel.nextDueDate.collectAsState()
    val isEnabled by viewModel.isEnabled.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Reminder") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveReminder()
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.Done, contentDescription = "Save Reminder")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { viewModel.onTitleChange(it) },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = type,
                onValueChange = { viewModel.onTypeChange(it) },
                label = { Text("Type") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = trigger,
                onValueChange = { viewModel.onTriggerChange(it) },
                label = { Text("Trigger") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = nextDueDate,
                onValueChange = { viewModel.onNextDueDateChange(it) },
                label = { Text("Next Due Date (yyyy-MM-dd)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isEnabled,
                    onCheckedChange = { viewModel.onIsEnabledChange(it) }
                )
                Text("Is Enabled")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.saveReminder()
                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReminderScreenPreview() {
    // AddReminderScreen(navController = rememberNavController()) // Can't preview with navController
}
