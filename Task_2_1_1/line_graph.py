import matplotlib.pyplot as plt

# Data for the graph
threads = [1, 2, 4, 8]
sequential = [1000, 1000, 1000, 1000]
parallel = [2000, 4000, 6000, 8000]
parallel_stream = [5000, 9000, 12000, 15000]

# Create a new figure and axis object
fig, ax = plt.subplots()

# Set the x and y axis labels and title
ax.set_xlabel('Number of Threads')
ax.set_ylabel('Operations/sec')
ax.set_title('Performance Comparison')

# Plot the data as lines
ax.plot(threads, sequential, label='Sequential')
ax.plot(threads, parallel, label='Parallel')
ax.plot(threads, parallel_stream, label='Parallel Stream')

# Add a legend to the graph
ax.legend()

# Display the graph
plt.show()
