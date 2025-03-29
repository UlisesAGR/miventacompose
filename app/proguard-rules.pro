# proguard-rules.pro - Module app
# Created by Ulises Gonzalez
# Copyright (c) 2025. All rights reserved

# Prevent deletion of annotations network and local
-keepattributes *Annotation*
-keep class kotlin.Metadata { *; }

# Preserve data class and sealed classes
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.** { *; }

# Avoid obfuscating inline functions
-keep class kotlin.coroutines.** { *; }

# Remove message for notnull annotations
-keep class org.jetbrains.annotations.** { *; }
-dontwarn org.jetbrains.annotations.**
