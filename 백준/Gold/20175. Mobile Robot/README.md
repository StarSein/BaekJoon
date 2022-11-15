# [Gold II] Mobile Robot - 20175 

[문제 링크](https://www.acmicpc.net/problem/20175) 

### 성능 요약

메모리: 17644 KB, 시간: 220 ms

### 분류

수학(math)

### 문제 설명

<p>Mobile robots are nowadays commonly used in various industrial and research sites. You are in charge of controlling <em>n</em> mobile robots that explore a very long, narrow and straight cave, which can be seen just as a line. The mobile robots collect data from the environment nearby and have effective mobility with their caterpillar tracks. You can control the <em>n</em> mobile robots on your control desk by a wireless control system. The <em>n</em> mobile robots you are controlling are labelled with numbers 1 to <em>n</em>, and are identified by robot 1, robot 2, …, robot <em>n</em> − 1, and robot <em>n</em>.</p>

<p>The mobile robots can also share their collected data to each other by a simple infrared communication protocol, while this robot-to-robot communication only works when the following very strict arrangement is completed for all the <em>n</em> mobile robots: the distance between robot <em>i</em> and robot <em>i</em> + 1 should be exactly <em>d</em> for all <em>i</em> = 1, 2, … , <em>n</em> − 1, where <em>d</em> is a prescribed positive real number, and no two robots should be at the same location in the cave. The location of each mobile robot in the cave is represented by a real number <em>x</em> since the cave is very long, very narrow, and very straight, so can be considered a line which stretches limitlessly in both directions. The distance between two mobile robots is thus calculated by the difference of their locations.</p>

<p>From the current locations of the mobile robots, they now need to share data to each other, and you are going to move them for the robot-to-robot communication. Since the robots are slow and simultaneously move at the same speed along the cave, you want to minimize the maximum distance each robot should travel to waste as little time as possible. During traveling, any two robots are assumed to safely pass by each other at the moment when both are at a common location in the cave. Note hence that currently two or more robots may be at a common location in the cave.</p>

<p>Given the current locations of the <em>n</em> mobile robots, write a program that computes their new locations for the robot-to-robot communication that minimizes the maximum distance each of the <em>n</em> robots travels and outputs the minimized maximum distance the robots should travel.</p>

### 입력 

 <p>Your program is to read from standard input. The input consists of exactly two lines. The first line consists of two integers, <em>n</em> and <em>d</em> (2 ≤ <em>n</em> ≤ 1,000,000 and 1 ≤ <em>d</em> ≤ 10<sup>10</sup>), where <em>n</em> denotes the number of mobile robots you are controlling and <em>d</em> is the distance that the robots should keep for the robot-to-robot communication. Each mobile robot is identified by a label from 1 to <em>n</em>. The second line consists of <em>n</em> integers, each of which ranges from −10<sup>16</sup> and 10<sup>16</sup>, representing the current locations of robot 1, robot 2, …, and robot <em>n</em> in this order.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line consisting of a real number, rounded to the first decimal place, that represents the minimum possible value of the maximum distance the mobile robots should travel for the robot-to-robot communication from the given current locations.</p>

