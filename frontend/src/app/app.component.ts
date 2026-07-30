import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

interface SummaryReport {
  totalActive: number;
  totalExpiringIn30Days: number;
  renewalRate: number;
}

interface ExpiringCert {
  id: number;
  certificationName: string;
  provider: string;
  employeeId: number;
  employeeName: string;
  issueDate: string;
  expiryDate: string;
  daysRemaining: number;
  status: string;
}

interface ProviderStat {
  name: string;
  count: number;
  percentage: number;
  color: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'frontend';
  
  summary: SummaryReport = {
    totalActive: 8400,
    totalExpiringIn30Days: 247,
    renewalRate: 94.0
  };

  expiringCerts: ExpiringCert[] = [
    {
      id: 1,
      certificationName: 'AWS Certified Solutions Architect - Associate',
      provider: 'Amazon Web Services (AWS)',
      employeeId: 101,
      employeeName: 'John Smith',
      issueDate: '2023-08-15',
      expiryDate: '2026-08-15',
      daysRemaining: 16,
      status: 'Valid'
    },
    {
      id: 2,
      certificationName: 'Oracle Certified Professional: Java SE 17 Developer',
      provider: 'Oracle',
      employeeId: 102,
      employeeName: 'Alice Johnson',
      issueDate: '2024-08-20',
      expiryDate: '2026-08-20',
      daysRemaining: 21,
      status: 'Valid'
    },
    {
      id: 3,
      certificationName: 'Certified Kubernetes Administrator (CKA)',
      provider: 'Cloud Native Computing Foundation (CNCF)',
      employeeId: 103,
      employeeName: 'Bob Vance',
      issueDate: '2024-08-10',
      expiryDate: '2026-08-10',
      daysRemaining: 11,
      status: 'Valid'
    },
    {
      id: 4,
      certificationName: 'Spring Certified Professional',
      provider: 'VMware Tanzu',
      employeeId: 104,
      employeeName: 'Charlie Brown',
      issueDate: '2023-08-28',
      expiryDate: '2026-08-28',
      daysRemaining: 29,
      status: 'Valid'
    }
  ];

  filteredCerts: ExpiringCert[] = [];

  // Provider Stats for visualization panel
  providers: ProviderStat[] = [
    { name: 'Amazon Web Services', count: 112, percentage: 45, color: '#3b82f6' },
    { name: 'Oracle', count: 75, percentage: 30, color: '#f59e0b' },
    { name: 'Cloud Native Computing Foundation', count: 60, percentage: 25, color: '#a855f7' }
  ];

  isLoaded = false;
  useMockData = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.filteredCerts = [...this.expiringCerts];
    this.fetchData();
  }

  fetchData(): void {
    // Attempt to load live data from backend APIs
    this.http.get<SummaryReport>('http://localhost:8080/api/certifications/reports/summary').subscribe({
      next: (summaryData) => {
        this.summary = summaryData;
        this.fetchExpiringCertifications();
      },
      error: (err) => {
        console.warn('Backend reporting API not available, falling back to spec-defined mock values.', err);
        this.useMockData = true;
        this.isLoaded = true;
      }
    });
  }

  fetchExpiringCertifications(): void {
    this.http.get<ExpiringCert[]>('http://localhost:8080/api/certifications/reports/expiring').subscribe({
      next: (expiringList) => {
        if (expiringList && expiringList.length > 0) {
          this.expiringCerts = expiringList;
          this.filteredCerts = [...this.expiringCerts];
          this.calculateProviderStats();
        }
        this.isLoaded = true;
      },
      error: (err) => {
        console.warn('Could not load expiring certifications list from backend.', err);
        this.useMockData = true;
        this.isLoaded = true;
      }
    });
  }

  calculateProviderStats(): void {
    // Dynamically aggregate provider counts if running on live DB list
    const counts: { [key: string]: number } = {};
    this.expiringCerts.forEach(c => {
      const prov = c.provider || 'Other';
      counts[prov] = (counts[prov] || 0) + 1;
    });

    const total = this.expiringCerts.length;
    if (total > 0) {
      const colors = ['#3b82f6', '#f59e0b', '#a855f7', '#10b981', '#6366f1'];
      this.providers = Object.keys(counts).map((key, index) => ({
        name: key,
        count: counts[key],
        percentage: Math.round((counts[key] / total) * 100),
        color: colors[index % colors.length]
      })).sort((a, b) => b.count - a.count);
    }
  }

  onSearch(event: Event): void {
    const query = (event.target as HTMLInputElement).value.toLowerCase().trim();
    if (!query) {
      this.filteredCerts = [...this.expiringCerts];
      return;
    }

    this.filteredCerts = this.expiringCerts.filter(cert => 
      cert.employeeName.toLowerCase().includes(query) ||
      cert.certificationName.toLowerCase().includes(query) ||
      cert.provider.toLowerCase().includes(query)
    );
  }
}
