try {
    Write-Host "Compiling production JAR via Gradle..." -ForegroundColor Cyan
    .\gradlew.bat bootJar

    if ($LASTEXITCODE -ne 0) {
        throw "Build compilation failed."
    }
}
finally {
    if ($restorationRequired) {
        Write-Host "Restoring development configuration..." -ForegroundColor Cyan
        Set-Content $PROP_FILE $configText
    }
}

Write-Host "Uploading orai.jar to VM via SSH..." -ForegroundColor Cyan
scp "build\libs\orai.jar" "vm:/home/ubuntu/orai/orai.jar"

Write-Host "Deployment successful!" -ForegroundColor Green

ssh vm "sudo systemctl restart orai"
Write-Host "App systemd service restarted via SSH!" -ForegroundColor Green