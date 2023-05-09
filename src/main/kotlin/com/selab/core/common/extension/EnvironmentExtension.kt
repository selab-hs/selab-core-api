package com.selab.core.common.extension

import com.selab.core.common.extension.EnvironmentType.PROFILE_PROD
import com.selab.core.common.extension.EnvironmentType.PROFILE_STAGING
import com.selab.core.common.extension.EnvironmentType.PROFILE_TEST
import org.springframework.core.env.Environment

fun Environment.isProd(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_PROD) }
}

fun Environment.isStaging(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_STAGING) }
}

fun Environment.isTest(): Boolean {
    return this.activeProfiles.any { it.equals(PROFILE_TEST) }
}

object EnvironmentType {
    const val PROFILE_PROD = "prod"
    const val PROFILE_STAGING = "staging"
    const val PROFILE_TEST = "test"
}
