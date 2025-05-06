<script setup lang="ts">
import 'survey-core/survey-core.css';
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import { Model } from 'survey-core'
import { SurveyComponent } from 'survey-vue3-ui'
import surveyData from '@/data/surveys.json'

// Get the survey ID from the route params
const route = useRoute()
const surveyId = computed(() => route.params.id as string)

// Type definition for our survey
interface SurveyItem {
  id: string
  title: string
  description: string
  questions: Record<string, any>
}

let survey: Model | null = null
const loading = ref(true)
const error = ref<string | null>(null)

onMounted(() => {
  // Find the survey with the matching ID
  const foundSurvey = surveyData.find(s => s.id === surveyId.value)
  
  if (foundSurvey) {
    // Create a SurveyJS model with the questions
    survey = new Model(foundSurvey.questions)
    
    // Handle survey completion
    survey.onComplete.add((sender) => {
      const results = JSON.stringify(sender.data)
      console.log('Survey results:', results)
      // In a real application, you would send this data to your backend
      alert("Thank you for completing the survey!")
    })
  } else {
    error.value = "Survey not found"
  }
  
  loading.value = false
})
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <div v-if="loading" class="text-center py-12">
      <p class="text-xl">Loading survey...</p>
    </div>
    
    <div v-else-if="error" class="text-center py-12 text-red-500">
      <p class="text-xl">{{ error }}</p>
      <router-link to="/surveys" class="mt-4 inline-block text-blue-500 hover:underline">
        Back to surveys
      </router-link>
    </div>
    
    <div v-else-if="survey">      
      <div class="bg-white p-6 rounded-lg shadow-md">
        <SurveyComponent :model="survey" />
      </div>
    </div>
  </div>
</template>
