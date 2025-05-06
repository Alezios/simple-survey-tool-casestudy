<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import surveyData from '@/data/surveys.json'

// Type definition for a survey
interface Survey {
  id: string
  title: string
  description: string
  questions: Record<string, any>
}

const surveys = ref<Survey[]>([])

onMounted(() => {
  surveys.value = surveyData
})
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <h1 class="text-2xl font-bold mb-6">Available Surveys</h1>
    
    <div class="grid md:grid-cols-2 gap-4">
      <div 
        v-for="survey in surveys" 
        :key="survey.id" 
        class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow"
      >
        <h2 class="text-xl font-semibold mb-2">{{ survey.title }}</h2>
        <p class="text-gray-600 mb-4">{{ survey.description }}</p>
        <RouterLink 
          :to="`/survey/${survey.id}`" 
          class="inline-block bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded transition-colors"
        >
          Take Survey
        </RouterLink>
      </div>
    </div>
  </div>
</template>