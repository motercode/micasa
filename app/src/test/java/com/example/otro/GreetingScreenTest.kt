package com.example.otro

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.otro.ui.screens.GreetingScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class GreetingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greetingScreen_showsWelcomeMessage() {
        // Set content to the GreetingScreen
        composeTestRule.setContent {
            GreetingScreen()
        }

        // Verify that the welcome message is displayed
        composeTestRule.onNodeWithText("Bienvenido a MiCasa").assertExists()
    }
}
