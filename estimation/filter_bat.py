import os
import glob

UID = 'u0a138'
results_dir = 'batt_results_filtered'

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
