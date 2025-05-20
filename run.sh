#!/bin/bash

INPUT_FILE="$1"
OUTPUT_FILE="$2"

if [ -z "$INPUT_FILE" ] || [ -z "$OUTPUT_FILE" ]; then
  echo "Usage: ./run.sh <input.json> <output.json>"
  exit 1
fi

mvn javafx:run -Djavafx.args="$INPUT_FILE $OUTPUT_FILE"
