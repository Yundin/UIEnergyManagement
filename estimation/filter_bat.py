#!/usr/bin/env python3

import os
import sys
import glob

UID = 'u0a662'
results_dir = 'batt_results_filtered'

if len(sys.argv) > 1:
    file_names = sys.argv[1:]
else:
    file_names = glob.glob("results/battery_*")

for name in file_names:
    f_in = open(name, 'r')
    name_split = name.split('/')
    if not os.path.isdir(results_dir):
        os.mkdir(results_dir)
    f_out = open(results_dir + '/' + name_split[1], 'w')

    line = f_in.readline()
    while not line.startswith('    Uid ' + UID):
        line = f_in.readline()
        if not line:
            raise SystemExit
    f_out.write(line)

    while not line.startswith('  ' + UID + ':'):
        line = f_in.readline()
        if not line:
            raise SystemExit

    f_out.write(line)
    line = f_in.readline()
    while line.startswith('    '):
        f_out.write(line)
        line = f_in.readline()
        if not line:
            break
