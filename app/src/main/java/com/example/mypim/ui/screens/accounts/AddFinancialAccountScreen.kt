package com.example.mypim.ui.screens.accounts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFinancialAccountScreen(
    navController: NavController,
    viewModel: AddFinancialAccountViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val type by viewModel.type.collectAsState()
    val balance by viewModel.balance.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Financial Account") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Add a new account")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Name") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = type,
                onValueChange = { viewModel.onTypeChange(it) },
                label = { Text("Type") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = balance,
                onValueChange = { viewModel.onBalanceChange(it) },
                label = { Text("Balance") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.saveFinancialAccount()
                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }
    }
}
