<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { surveyApi } from '@/api/surveyApi'
import type { Survey } from '@/api/types/survey'
import { PresentationChartLineIcon } from '@heroicons/vue/16/solid'


const surveys = ref<Survey[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

onMounted(async () => {
  try {
    surveys.value = await surveyApi.getAllSurveys()
  } catch (err) {
    console.error('Failed to fetch surveys:', err)
    error.value = 'Failed to load surveys. Please try again later.'
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <h1 class="text-2xl font-bold mb-6">Available Surveys</h1>

    <div v-if="loading" class="text-center py-12">
      <p class="text-xl">Loading surveys...</p>
    </div>

    <div v-else-if="error" class="text-center py-12 text-red-500">
      <p class="text-xl">{{ error }}</p>
    </div>

    <div v-else class="grid md:grid-cols-2 gap-4">
      <div v-for="survey in surveys" :key="survey.id"
        class="relative bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">

        <RouterLink class="absolute group right-2 bottom-2" :to="`/api/surveys/${survey.id}/results`">
          <PresentationChartLineIcon class="h-5 w-5 text-blue-600" />
          <span
            class="absolute bottom-full right-0 mb-2 hidden group-hover:block bg-gray-900 text-white text-sm px-3 py-2 rounded-lg">
            View Results (JSON)
            <span class="absolute top-full right-4 -mt-1 border-4 border-transparent border-t-gray-900"></span>
          </span>
        </RouterLink>
        <h2 class="text-xl font-semibold mb-2">{{ survey.title }}</h2>
        <p class="text-gray-600 mb-4">{{ survey.description }}</p>
        <RouterLink :to="`/survey/${survey.id}`"
          class="inline-block bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded transition-colors">
          Take Survey
        </RouterLink>
      </div>
    </div>
  </div>
</template>