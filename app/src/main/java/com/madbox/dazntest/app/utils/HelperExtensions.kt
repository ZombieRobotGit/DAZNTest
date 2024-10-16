package com.madbox.dazntest.app.utils

import androidx.navigation.NavHostController

fun NavHostController.navigateAvoidingBackStack(route: String){
    navigate(route){
        // Avoid building up the backstack
        popUpTo(this@navigateAvoidingBackStack.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
