$ErrorActionPreference = "Stop"

$resultFile = Join-Path $env:WORKSPACE "automation-result.txt"

if (!(Test-Path $resultFile)) {
    Write-Host "Result file not found: $resultFile"
    exit 1
}

$result = Get-Content $resultFile -Raw

Write-Host "Result file content:"
Write-Host $result

$body = @{
    text = $result
} | ConvertTo-Json

$webhookUrl = "https://defaultcb556afa238c4722beeeedc04e7719.b0.environment.api.powerplatform.com:443/powerautomate/automations/direct/cu/24/workflows/c6bdbe001d724fada3303aa9a56925e1/triggers/manual/paths/invoke?api-version=1&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=W4qO3cOP7OTHaC8zgSZNNJfnm7mCTw1Bvs2AVPaCaik"

try {

    $response = Invoke-RestMethod `
        -Uri $webhookUrl `
        -Method Post `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Power Automate response:"
    Write-Host $response

    Write-Host "Automation result sent to Teams successfully."

}
catch {

    Write-Host "FAILED to send automation result to Teams."
    Write-Host $_.Exception.Message

    if ($_.ErrorDetails.Message) {
        Write-Host $_.ErrorDetails.Message
    }

    exit 1
}