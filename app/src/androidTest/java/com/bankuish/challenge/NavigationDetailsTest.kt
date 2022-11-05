package com.bankuish.challenge

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bankuish.challenge.di.KoinTestRule
import com.bankuish.challenge.di.instrumentedTestModule
import com.bankuish.challenge.views.search.SearchFragment
import com.bankuish.challenge.views.search.SearchFragmentDirections
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock

@RunWith(AndroidJUnit4::class)
class NavigationDetailsTest {

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(instrumentedTestModule)
    )

    @Test
    fun navigateFromSearchFragmentToDetailsFragment_destinationIsDetailsFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val searchFragment = launchFragmentInContainer<SearchFragment>(themeResId = R.style.Theme_BankuishChallenge)

        searchFragment.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        searchFragment.withFragment {
            this.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(repository = mock()))
        }

        assertEquals(navController.currentDestination?.id, R.id.details_fragment)
    }

}