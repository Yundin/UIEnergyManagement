#!/bin/zsh

TIME_MS=180000
#VIEWS_COUNT=27
VIEWS_COUNT=1

main() {
  adb shell exit || exit 1

  [ -d "results" ] && check_result_dir
  mkdir "results"

  for i in {0..$((VIEWS_COUNT - 1))} ; do
      echo "Testing view $i..."
      adb shell dumpsys batterystats --reset || exit 2
      echo "Batterystats reset success"

      START=$(date +'%Y-%m-%d %T')
      echo $START
      echo "Testing..."
      adb shell am instrument -w \
      -e class com.yundin.estimation.EstimationTest#testViewByIndex \
      -e index $i \
      -e time $TIME_MS \
      com.yundin.estimation.test/com.yundin.estimation.MyTestRunner || exit 5
      END=$(date +'%Y-%m-%d %T')
      echo "Testing completed"

      echo "Dumping..."
      adb shell dumpsys batterystats --write || exit 6
      adb shell dumpsys batterystats > "results/battery_$i" || exit 3
      echo $START > "results/cpu_$i"
      echo $END >> "results/cpu_$i"
      #adb shell dumpsys cpuinfo >> "results/cpu_$i" || exit 4
      echo "Dumpsys complete successfully\n"
  done

  cpuinfo=$(adb shell dumpsys cpuinfo)
  for f in results/cpu_*; do
      echo $cpuinfo >> $f
  done
}

check_result_dir() {
  while true; do
    echo -n "Results dir exists, delete? [y/n]: "
    read ans
    if [[ $ans == 'y' ]]; then
      rm -rf results
      break
    elif [[ $ans == 'n' ]]; then
      echo "Return when you are ready"
      exit 0
    fi
  done
}

main "$@"; exit
