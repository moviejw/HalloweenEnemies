@echo off
REM fixalpha 자동 실행 (원본 덮어쓰기)
REM drag and drop your file onto this batch file
if "%~1"=="" (
    echo Drop an image file onto this batch file.
    pause
    exit /b
)
C:\Users\User\Modding\Sts1\HalloweenEnemies\src\main\resources\images\monsters\fixalpha.exe mul "%~1" "%~1"
echo Done! File overwritten: %~1
pause
