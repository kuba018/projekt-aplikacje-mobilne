#!/bin/bash

find . \
  -path "./.git" -prune -o \
  -path "./.gradle" -prune -o \
  -path "./build" -prune -o \
  -path "./app/build" -prune -o \
  -path "./.idea" -prune -o \
  -type f \( \
    -name "*.kt" -o \
    -name "*.kts" -o \
    -name "*.xml" -o \
    -name "*.md" -o \
    -name "gradle.properties" -o \
    -name "proguard-rules.pro" \
  \) -print | sort | while read -r f; do
    printf '\n\n===== FILE: %s =====\n\n' "$f"
    sed -n '1,300p' "$f"
  done > project_dump.txt