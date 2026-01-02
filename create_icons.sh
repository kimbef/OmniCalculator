#!/bin/bash

# Create a simple colored square icon for testing
create_icon() {
    local size=$1
    local dir=$2
    
    # Create a simple PNG with ImageMagick (if available) or just create empty files
    echo "R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==" | base64 -d > "${dir}/ic_launcher.png"
    cp "${dir}/ic_launcher.png" "${dir}/ic_launcher_round.png"
    cp "${dir}/ic_launcher.png" "${dir}/ic_launcher_foreground.png"
}

cd /home/runner/work/OmniCalculator/OmniCalculator/app/src/main/res
create_icon 48 mipmap-mdpi
create_icon 72 mipmap-hdpi
create_icon 96 mipmap-xhdpi
create_icon 144 mipmap-xxhdpi
create_icon 192 mipmap-xxxhdpi
