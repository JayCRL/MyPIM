package com.example.mypim.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mypim.ui.screens.accounts.AddFinancialAccountScreen
import com.example.mypim.ui.screens.accounts.FinancialAccountsScreen
import com.example.mypim.ui.screens.items.AddItemScreen
import com.example.mypim.ui.screens.items.ItemsScreen
import com.example.mypim.ui.screens.reminders.AddReminderScreen
import com.example.mypim.ui.screens.reminders.RemindersScreen
import com.example.mypim.ui.screens.transactions.AddTransactionScreen
import com.example.mypim.ui.screens.transactions.TransactionsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.ItemsScreen.route) {
        composable(Screen.ItemsScreen.route) {
            ItemsScreen(navController = navController)
        }
        composable(Screen.FinancialAccountsScreen.route) {
            FinancialAccountsScreen(navController = navController)
        }
        composable(Screen.AddItemScreen.route) {
            AddItemScreen(navController = navController)
        }
        composable(Screen.AddFinancialAccountScreen.route) {
            AddFinancialAccountScreen(navController = navController)
        }
        composable(Screen.TransactionsScreen.route) {
            TransactionsScreen(navController = navController)
        }
        composable(Screen.AddTransactionScreen.route) {
            AddTransactionScreen(navController = navController)
        }
        composable(Screen.RemindersScreen.route) {
            RemindersScreen(navController = navController)
        }
        composable(Screen.AddReminderScreen.route) {
            AddReminderScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object ItemsScreen : Screen("items_screen")
    object AddItemScreen : Screen("add_item_screen")
    object FinancialAccountsScreen : Screen("financial_accounts_screen")
    object AddFinancialAccountScreen : Screen("add_financial_account_screen")
    object TransactionsScreen : Screen("transactions_screen")
    object AddTransactionScreen : Screen("add_transaction_screen")
    object RemindersScreen : Screen("reminders_screen")
    object AddReminderScreen : Screen("add_reminder_screen")
}
