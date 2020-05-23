#!/usr/bin/env python3

import sys

def main():
    if len(sys.argv) > 1:
        file_names = sys.argv[1:]
    else:
        print('No arguments provided')
        exit()

    for name in file_names:
        lines = []
        with open(name, 'r+') as f:
            for line in f:
                lines.append(line)
                if line.startswith('u='):
                    lines.append(str(proceed_line(line)) + '\n')

            f.seek(0)
            f.writelines(lines)

def proceed_line(line):
    user = line.find('=') + 1
    sys = line.find('s=')
    user_time = line[user:sys-1]
    sys_time = line[sys+2:]
    return parse_ms(user_time) + parse_ms(sys_time)

def parse_ms(amount):
    minutes = seconds = ms = 0
    start = 0
    i = 1
    while i < len(amount):
        if amount[i].isnumeric():
            i += 1
        else:
            number = int(amount[start:i])
            if amount[i] == 's':
                seconds = number
            elif amount[i:i+2] == 'ms':
                ms = number
                return minutes * 60 * 1000 + seconds * 1000 + ms
            else:
                minutes = number
            start = amount.find(' ', i) + 1
            i = start + 1

if __name__ == "__main__":
    main()
