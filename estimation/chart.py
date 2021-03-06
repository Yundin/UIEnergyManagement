#!/usr/bin/env python2

import os
import sys
import matplotlib
import matplotlib.pyplot as plt
import numpy as np

path = sys.argv[1]
if os.path.exists(path):
    file = open(path, 'r')
    names = []
    mAh = []
    time = []

    i = 0
    for line in file:
        no_new = line[:-1]
        index = i % 4
        if index == 0:
            names.append(no_new)
        elif index == 1:
            mAh.append(float(no_new))
        elif index == 2:
            time.append(float(no_new))
        i += 1

    sorted_list = sorted(zip(time, mAh, names))
    names = [n for _,_,n in sorted_list]
    mAh = [m for _,m,_ in sorted_list]
    time = [t for t,_,_ in sorted_list]

    plt.rcdefaults()
    fig, ax = plt.subplots()
    ax2 = ax.twiny()

    h = 0.25
    y_pos = np.arange(len(names))

    rects1 = ax.barh(y_pos - h/2, mAh, h, label='mAh', color='r', align='center')
    rects2 = ax2.barh(y_pos + h/2, time, h, label='proc ms', color='b', align='center')

    ax.set_yticks(y_pos)
    ax.set_yticklabels(names)

    ax.set_xlabel('mAh', color='r')
    ax.tick_params(axis='x', labelcolor='r')

    ax2.set_xlabel('proc ms', color='b')
    ax2.tick_params(axis='x', labelcolor='b')

    plt.show()
