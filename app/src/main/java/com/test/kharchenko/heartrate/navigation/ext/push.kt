package com.test.kharchenko.heartrate.navigation.ext

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator

/***
 * extension for voyager [Navigator] for push screen by provider Type
 *
 * @param provider screen provider used to obtain the screen instance to be pushed.
 */
inline fun <reified T : ScreenProvider> Navigator.push(provider: T) {
    this.push(ScreenRegistry.get(provider))
}