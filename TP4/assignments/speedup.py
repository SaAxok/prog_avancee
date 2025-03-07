import os
import matplotlib.pyplot as plt

ntot = 12000000
for workers in [1, 2, 3, 4, 5, 6, 8, 10, 12]:
   for i in range(10):
       print(f"Workers: {workers}")
       os.system(f"java Assignment102.java {ntot} {workers}")
       print(" ")

with open("./out-assignments102-G26.txt", 'r') as file:
    lines = file.readlines()

# Parsing
worker_times = {}
for line in lines:
    parts = line.split(" ")
    worker = int(parts[2])
    time = float(parts[3])
    if worker not in worker_times:
        worker_times[worker] = []
    worker_times[worker].append(time)

# Calculer la moyenne du temps par nombre de workers
workers = sorted(worker_times.keys())
average_times = [sum(worker_times[worker]) / len(worker_times[worker]) for worker in workers]

# Calculer le speedup
T1 = sum(worker_times[1]) / len(worker_times[1])
speedup = [T1 / Tp for Tp in average_times]

# Affichage du speedup
plt.figure()
plt.plot(workers, speedup, marker='o', label='Observed Speedup')
plt.plot(workers, workers, 'r--', label='Ideal Linear Speedup')  # Courbe idéale en pointillé
<<<<<<< HEAD
plt.xticks(range(1, 13)) 
plt.yticks(range(1, 13)) 
=======
plt.xticks(range(1, 13))
plt.yticks(range(1, 13))
>>>>>>> 9be75d06bca8e17dcbb8fe51bfe6996bea425890
plt.xlabel('Number of Workers')
plt.ylabel('Speedup')
plt.title('Speedup vs Number of Workers')
plt.grid(True)
plt.axis('equal')
plt.legend()
plt.savefig('speedup_plot.png')
plt.show()