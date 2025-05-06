<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
  <main>
    <div class="container mx-auto px-4 py-12 text-center">
      <h1 class="text-4xl font-bold mb-6">Welcome to the Team Survey Tool</h1>
      
      <p class="text-xl mb-8">
        A simple tool to create and participate in anonymous surveys.
        Rate questions and provide valuable feedback.
      </p>
      
      <RouterLink 
        to="/surveys" 
        class="inline-block bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-lg text-lg font-medium transition-colors"
      >
        View Available Surveys
      </RouterLink>
    </div>
  </main>
</template>
