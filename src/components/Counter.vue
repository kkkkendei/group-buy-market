<template>
  <div class="counter">
    <h2>计数器</h2>
    <div class="counter-content">
      <button @click="decrease" class="btn">-</button>
      <span class="count">{{ count }}</span>
      <button @click="increase" class="btn">+</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Counter',
  data() {
    return {
      count: 0
    }
  },
  methods: {
    increase() {
      this.count++
    },
    decrease() {
      if (this.count > 0) {
        this.count--
      }
    }
  }
}
</script>

<style scoped>
.counter {
  text-align: center;
  padding: 20px;
  max-width: 300px;
  margin: 0 auto;
}

.counter-content {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 20px;
  margin-top: 20px;
}

.btn {
  width: 40px;
  height: 40px;
  border: none;
  border-radius: 50%;
  background-color: #4CAF50;
  color: white;
  font-size: 20px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #45a049;
}

.count {
  font-size: 24px;
  font-weight: bold;
  min-width: 50px;
}
</style> 