/*
 * HandleError.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.util

import android.content.Context
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.miventa.compose.mobile.R
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Context.handleError(exception: Throwable?): String {

    log(message = exception.toString())

    return when (exception) {
        is UnknownHostException, is ConnectException, is SocketException -> getString(R.string.check_your_internet_connection)
        is SocketTimeoutException, is TimeoutException -> getString(R.string.timeout)

        is ValidateYourEmailException -> getString(R.string.please_verify_your_email)

        is FirebaseAuthUserCollisionException -> getString(R.string.email_is_already_registered)
        is FirebaseAuthInvalidUserException -> getString(R.string.invalid_user)
        is FirebaseAuthInvalidCredentialsException -> getString(R.string.invalid_credentials)
        is FirebaseTooManyRequestsException -> getString(R.string.we_have_blocked_all_request_from_this_device_due_to_unusual_activity_try_again_later)
        is FirebaseNetworkException -> getString(R.string.check_your_internet_connection)

        else -> getString(R.string.please_try_again_later)
    }
}
