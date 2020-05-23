#!/usr/bin/env python3

import sys
import os
import statistics

real_names = ['AutoCompleteTextView', 'Button', 'CalendarView', 'CheckBox', 'CheckedTextView', 'Chronometer', 'DatePicker', 'EditText', 'ImageButton', 'ImageSwitcher', 'ImageView', 'MultiAutoCompleteTextView', 'NumberPicker', 'ProgressBar', 'RadioButton', 'RatingBar', 'SearchView', 'SeekBar', 'Space', 'Switch', 'TextClock', 'TextSwitcher', 'TextView', 'TimePicker', 'ToggleButton', 'VideoView', 'View']

if len(sys.argv) < 3:
    print('Too few arguments')
    exit()
else:
    file_names = sys.argv[1:]

files = []
for name in file_names:
    with open(name) as f:
        files.append(f.readlines())

out = open('median', 'w')
count = len(files)
lines_count = len(files[0])
for i in range(lines_count):
    index = i % 5
    if index == 2:
        continue
    if index == 4:
        out.write('\n')
        continue
    if index == 0:
        name = files[0][i]
        num = name.split('battery_')[1][:-1]
        real_name = real_names[int(num)] + '\n'
        out.write(real_name)
        continue
    lines = [f[i] for f in files]
    items = [float(l[:-1]) for l in lines]
    median = statistics.median(items)
    out.write(str(median) + '\n')

out.flush()
out.close()
