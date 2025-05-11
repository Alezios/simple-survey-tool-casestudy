<script setup lang="ts">
import 'survey-core/survey-core.css';
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { Model } from 'survey-core'
import { SurveyComponent } from 'survey-vue3-ui'
import { surveyApi } from '@/api/surveyApi'

// Get the survey ID from the route params
const route = useRoute()
const router = useRouter()
const surveyId = computed(() => route.params.id as string)

let survey: Model | null = null
const loading = ref(true)
const error = ref<string | null>(null)
const submitting = ref(false)

onMounted(async () => {
  try {
    const foundSurvey = await surveyApi.getSurveyById(surveyId.value)
    
    survey = new Model({elements: foundSurvey.questions})
    console.log({elements: foundSurvey.questions})
    
    survey.onComplete.add(async (sender) => {
      try {
        submitting.value = true
        await surveyApi.submitSurveyResponse(surveyId.value, sender.data)
        alert("Thank you for completing the survey!")
        router.push('/surveys')
      } catch (err) {
        console.error('Failed to submit survey:', err)
        alert("There was an error submitting your survey. Please try again.")
      } finally {
        submitting.value = false
      }
    })
  } catch (err) {
    console.error('Failed to fetch survey:', err)
    error.value = "Survey not found or error loading survey"
  } finally {
    loading.value = false
  }
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
