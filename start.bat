@echo off
cd /d "%~dp0"
java -cp "lib/*;src" com.companybase.application.Engine
pause
