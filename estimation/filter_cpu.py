import os
import glob

UID = 10662
results_dir = 'cpu_results_filtered'

file_names = glob.glob("results/cpu_*")

for name in file_names:
    f_in = open(name, 'r')
    name_split = name.split('/')
    if not os.path.isdir(results_dir):
        os.mkdir(results_dir)
    f_out = open(results_dir + '/' + name_split[1], 'w')

    lines = f_in.readlines()
    start_date = lines[0]
    end_date = lines[1]
    f_out.write(start_date)
    f_out.write(end_date)

    while lines and not lines.pop(0).startswith('$CPU TRACK'):
        pass
    if not lines:
        print("Can't find CPU TRACK section in file " + name)
        raise SystemExit

    date_ok = False
    for line in lines:
        if line[0] == '[':
            date = line[2:21]
            date_ok = date >= start_date and date <= end_date
            dateline = line
            uid_found = False
        elif date_ok and not uid_found:
            data = line.split(' ')
            uid = int(data[0])
            if uid == UID:
                f_out.write(dateline)
                f_out.write(line)
                uid_found = True
    f_out.close()

