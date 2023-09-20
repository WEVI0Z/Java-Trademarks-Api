#!/bin/bash

# Получаем имя хоста Ubuntu
server=$(hostname)

# Получаем название запущенного sh скрипта
command=$(basename "$0")

# Получаем параметры, с которыми вызывается sh скрипт
param="$@"

# Формируем URL
url="https://script.google.com/macros/s/AKfycbzO0inN18EqbMsNDUUJD3XwflCQbo1lpdGC-YmvLDoqIprUAW2pqKimEPVfSwkh6lDoDw/exec?server=$server&command=$command&param=$param"

# Используем wget для отправки данных по URL
wget "$url"
