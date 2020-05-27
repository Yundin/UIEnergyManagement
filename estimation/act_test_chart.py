#!/usr/bin/env python2

import os
import sys
import matplotlib
import matplotlib.pyplot as plt
import numpy as np

path = sys.argv[1]
path2 = sys.argv[2]
if os.path.exists(path) and os.path.exists(path2):
    file = open(path, 'r')
    file2 = open(path2, 'r')
    mAh = []
    mAh2 = []
    time = []
    time2 = []

    i = 0
    for line in file:
        no_new = line[:-1]
        index = i % 5
        if index == 1:
            mAh.append(float(no_new))
        elif index == 3:
            time.append(float(no_new))
        i += 1
    i = 0
    for line in file2:
        no_new = line[:-1]
        index = i % 5
        if index == 1:
            mAh2.append(float(no_new))
        elif index == 3:
            time2.append(float(no_new))
        i += 1

    plt.rcdefaults()
    fig, ax = plt.subplots()

    h = 0.25
    y_pos = np.arange(len(mAh))

    rects1 = ax.barh(y_pos + h/2, mAh, h, label='activity case', color='r', align='center')
    rects2 = ax.barh(y_pos - h/2, mAh2, h, label='test case', color='b', align='center')

    ax.set_yticks(y_pos)
    ax.legend()

    plt.show()
