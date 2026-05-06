export class TestDataGenerator {
  static generateUniqueEmail(prefix: string = 'testuser'): string {
    const timestamp = Date.now();
    const random = Math.floor(Math.random() * 10000);
    return `${prefix}_${timestamp}_${random}@bank.com`;
  }

  static generateUserName(firstName?: string, lastName?: string): string {
    const firstNames = ['John', 'Jane', 'Michael', 'Sarah', 'David', 'Emily'];
    const lastNames = ['Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Garcia'];
    
    const first = firstName || firstNames[Math.floor(Math.random() * firstNames.length)];
    const last = lastName || lastNames[Math.floor(Math.random() * lastNames.length)];
    
    return `${first} ${last}`;
  }

  static generatePhoneNumber(countryCode: string = '+1'): string {
    const areaCode = Math.floor(Math.random() * 900) + 100;
    const firstPart = Math.floor(Math.random() * 900) + 100;
    const secondPart = Math.floor(Math.random() * 9000) + 1000;
    
    return `${countryCode}-${areaCode}-${firstPart}-${secondPart}`;
  }

  static generateUserData() {
    return {
      userName: this.generateUserName(),
      email: this.generateUniqueEmail(),
      contact: this.generatePhoneNumber()
    };
  }
}