try { 
    Write-Host "Compiling CSS..." -ForegroundColor Cyan
    npm run tailwind:minify

    Write-Host "Compiling production JAR via Gradle..." -ForegroundColor Cyan
    .\gradlew.bat bootJar

    if ($LASTEXITCODE -ne 0) {
        throw "Build compilation failed."
    }
} finally {}

ssh vm "sudo systemctl stop orai"
Write-Host "App systemd service stopped via SSH!" -ForegroundColor Green

Write-Host "Uploading files to VM via SSH..." -ForegroundColor Cyan
scp "build\libs\orai.jar" "vm:/home/ubuntu/orai/orai.jar"

Write-Host "Deployment successful!" -ForegroundColor Green

ssh vm "sudo systemctl start orai"
Write-Host "App systemd service started via SSH!" -ForegroundColor Green